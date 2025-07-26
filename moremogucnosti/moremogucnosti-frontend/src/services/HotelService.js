import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/hoteli'

export const listHoteli = () => axios.get(REST_API_BASE_URL);

export const createHotel = (hotel) => axios.post(REST_API_BASE_URL, hotel);

export const deleteHotel = (id) => axios.delete(REST_API_BASE_URL + '/' + id);

export const updateHotel = (id, hotel) => axios.put(REST_API_BASE_URL + '/' + id, hotel);

export const getHotel = (id) => axios.get(REST_API_BASE_URL + '/' + id);