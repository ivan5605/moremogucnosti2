import React from 'react'
import { useNavigate } from 'react-router-dom'

const IzbornikComponent = () => {

  const navigator = useNavigate();

  // function prikaziHotele() {
  //   navigator('/hoteli'); // Navigira na stranicu s hotelima
  // } 
  // koristim lambda funkciju umjesto ove funkcije

  return (
    <div>
      <button type='button' className='btn btn-outline-danger m-3' onClick={() => navigator('/hoteli')}>Hoteli</button>
      <button type='button' className='btn btn-outline-secondary m-3' onClick={() => navigator('/korisnici')}>Korisnici</button>
    </div>
  )
}

export default IzbornikComponent