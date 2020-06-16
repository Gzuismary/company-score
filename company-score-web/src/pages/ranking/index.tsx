import React, { useEffect, useState } from 'react';
import './styles.css';
import Api from '../../service/api';
import { Link } from 'react-router-dom';

interface Company {
    id: number;
    name: string;
    score: number
}

const Ranking = () => {
    const [companies, setCompanies] = useState<Company[]>([{ id: 0, name: '', score: 0 }]);

    useEffect(() => {
        Api.get<Company[]>('company')
            .then(response => {
                setCompanies(response.data);
            })
            .catch(erro => console.log('Erro'))
    }, [])

    function sortRankingData(dadoFinanceiro1: Company, dadoFinanceiro2: Company) {
        if (dadoFinanceiro1.score > dadoFinanceiro2.score) {
            return -1;
          }
          if (dadoFinanceiro1.score < dadoFinanceiro2.score) {
            return 1;
          }
          
          return 0;
    }

    return (
        <>
            <div className="wrapper">
                <div className="table">
                    <div className="row header">
                        <div className="cell">
                            Nome
                    </div>
                        <div className="cell">
                            Score
                    </div>
                    </div>
                    {companies.sort((f1, f2) => sortRankingData(f1, f2)).map(company => (
                        <div key={company.id} className="row">
                            <div className="cell" data-title="Nome">
                                {company.name}
                            </div>
                            <div className="cell" data-title="Score">
                                {company.score}
                            </div>
                        </div>
                    ))}
                </div>
            </div>
            <footer>
                <Link to="/">
                    <strong>Voltar</strong>
                </Link>
            </footer>
        </>
    );
}

export default Ranking;