import React, { FC } from 'react';
import { Link } from 'react-router-dom';

import logo from '../../assets/logo_SERASA_topositeMENOR.png';
import './styles.css';

const Home: FC = () => {
    return (
        <div id="page-home">
            <div className="content">
                <header>
                    <img src={logo} alt="Serasa" />
                </header>
                <main>
                    <Link to="/upload">
                        <strong>Upload CSV</strong>
                    </Link>
                    <Link to="/ranking">
                        <strong>Ranking de empresas</strong>
                    </Link>
                </main>
            </div>
        </div>
    );
}

export default Home;