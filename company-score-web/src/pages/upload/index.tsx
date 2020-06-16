import React, { useEffect, useState, ChangeEvent, FormEvent } from 'react';
import { useDropzone } from 'react-dropzone';
import { FiFileText } from 'react-icons/fi';
import Api from '../../service/api';
import './style.css';
import { Link, useHistory } from 'react-router-dom';

interface Company {
    id: number;
    name: string;
}

const Upload = () => {
    const history = useHistory();
    const { acceptedFiles, getRootProps, getInputProps } = useDropzone();
    const [companies, setCompanies] = useState<Company[]>([{id: 0, name: ''}]);
    const [ company, setCompany ] = useState<number>();

    useEffect(() => {
        Api.get<Company[]>('company')
            .then(response => {
                setCompanies(response.data)
            })
            .catch(error => {
                console.log(error);
            })
    }, []);

    function handleGoBack() {
        history.push("/");
    }

    function handleCompanyChange(event: ChangeEvent<HTMLSelectElement>) {
        const { value } = event.target;

        console.log(value);
        
    
        if (!value) {
          return;
        }
    
        setCompany(Number(value));
    }

    function submitFile(event: FormEvent) {
        event.preventDefault();
        const formData = new FormData();

        formData.append('companyId', String(company));
        formData.append('file', acceptedFiles[0]);

        Api.post('financial-data', formData)
        .then(() => {
            alert('Arquivo enviado');

        })
        .catch(() => alert('Erro no envio'))
    }

    const files = (
        <aside id="file-list">
            <span><FiFileText size={26}/></span>
            <br/>
            {acceptedFiles.map(file => (
                file.name
            ))}
        </aside>
    );

    return (
        <form className="container column">
            <header>
                <h1>Escolha o arquivo csv no formato necessário para o upload</h1>
            </header>
            <div>
                <div {...getRootProps({ className: 'dropzone' })}>
                    <input {...getInputProps()} type="file" accept=".csv" />
                    {acceptedFiles.length > 0
                        ? files
                        : <p>Drag 'n' drop some files here, or click to select files</p>}
                </div>
            </div>
            <select name="company" id="company" onChange={handleCompanyChange}>
                <option value="0">Selecione uma empresa</option>
                {companies.map(company => {
                    return (
                        <option key={company.id} value={company.id}>{company.name}</option>
                    );
                })}
            </select>
            <section>
                <button type="button" onClick={submitFile} disabled={!company || acceptedFiles.length < 1}>
                    Enviar
                </button>
                <button onClick={handleGoBack}>
                    <strong>Voltar</strong>
                </button>
            </section>
        </form>
    );
}

export default Upload;