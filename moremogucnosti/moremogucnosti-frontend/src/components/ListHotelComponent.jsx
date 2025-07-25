import React, { useEffect, useState } from 'react' //useState koristi se za upravljanje stanjem komponente
import { listHoteli, deleteHotel } from '../services/HotelService'
// useEffect koristi se za upravljanje efektima u komponenti, kao što su API pozivi ili manipulacija DOM-om
import { useNavigate } from 'react-router-dom'

const ListHotelComponent = () => {

  const navigator = useNavigate(); // koristi se za navigaciju između stranica

  const [hotels, setHotels] = useState([]) //useState služi za inicijalizaciju stanja komponente

  useEffect(() => {
    getAllHotels(); // poziva funkciju za dohvat svih hotela prilikom učitavanja komponente
  }, [])

  function getAllHotels() {
    listHoteli().then(response => {
      setHotels(response.data) //postavlja podatke hotela u stanje komponente
    }).catch(error => {
      console.error("Greška kod dohvacanja hotela!", error);
    });
  }

  function obrisiHotel(id) {
    console.log("Brisanje hotela s ID-jem:", id);
    deleteHotel(id).then(response => {
      console.log("Hotel uspješno obrisan!", response.data);
      getAllHotels(); // ponovno dohvaća sve hotele nakon brisanja
    }).catch(error => {
      console.error("Greška kod brisanja hotela!", error);
    })
  }

  return (
    <div className='container'>
      <h1 className='text-center'>List of hotels</h1>
      <button type='button' className='btn btn-outline-primary mb-2' onClick={() => navigator('/editHotel')}>Dodaj hotel</button>
      <table className='table table-hover table-bordered'>
        <thead>
          <tr>
            <th>Id</th>
            <th>Naziv</th>
            <th>Lokacija</th>
            <th>Cijena noćenja</th>
            <th>Obrisi/Uredi</th>
          </tr>
        </thead>

        <tbody>
          {
            hotels.map(hotel =>
              <tr key={hotel.id}>
                <td>{hotel.id}</td>
                <td>{hotel.naziv}</td>
                <td>{hotel.lokacija}</td>
                <td>{hotel.cijenaNocenja}</td>
                <td>
                  <button type='button' className='btn btn-outline-danger m-1' onClick={() => obrisiHotel(hotel.id)}>Obriši</button>
                  <button type='button' className='btn btn-outline-success m-1'>Uredi</button>
                </td>
              </tr>
            )
          }
        </tbody>
      </table>
      <button type='button' className='btn btn-outline-danger' onClick={() => navigator('/')}>Natrag</button>

    </div>
  )
}

export default ListHotelComponent