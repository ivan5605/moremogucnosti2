import axios from 'axios';

const REST_API_BASE_URL = 'http://localhost:8080/api/korisnici';

export const listKorisnici = () => axios.get(REST_API_BASE_URL);

export const addKorisnik = (korisnik) => axios.post(REST_API_BASE_URL, korisnik);

export const deleteKorisnik = (id) => axios.delete(`${REST_API_BASE_URL}/${id}`);

export const updateKorisnik = (id, korisnik) => axios.put(`${REST_API_BASE_URL}/${id}`, korisnik);

export const getKorisnik = (id) => axios.get(REST_API_BASE_URL + '/' + id);