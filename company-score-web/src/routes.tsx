import React from 'react';
import { BrowserRouter, Route } from 'react-router-dom';

import Home from './pages/home';
import Upload from './pages/upload';
import Ranking from './pages/ranking';

const Routes = () => {
    return (
        <BrowserRouter>
            <Route component={Home} path="/" exact/>
            <Route component={Upload} path="/upload" exact/>
            <Route component={Ranking} path="/ranking" exact/>
        </BrowserRouter>
    );
}

export default Routes;