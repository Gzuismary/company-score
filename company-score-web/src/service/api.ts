import Axios from 'axios';

const Api = Axios.create({
    baseURL: 'http://localhost:8080'
});

export default Api;