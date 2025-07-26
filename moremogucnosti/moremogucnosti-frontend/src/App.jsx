import { BrowserRouter, Routes, Route } from 'react-router-dom'
import './App.css'
import FooterComponent from './components/FooterComponent'
import HeaderComponent from './components/HeaderComponent'
import IzbornikComponent from './components/IzbornikComponent'
import ListHotelComponent from './components/ListHotelComponent'
import HotelComponent from './components/HotelComponent'
import ListKorisnikComponent from './components/ListKorisnikComponent'
import KorisnikComponent from './components/KorisnikComponent'

function App() {


  return (
    <>
      <BrowserRouter>
        <HeaderComponent />
        <Routes>
          {/* http://localhost:3000 */}
          <Route path='/' element={<IzbornikComponent />}></Route>
          {/* http://localhost:3000/hoteli */}
          <Route path='/hoteli' element={<ListHotelComponent />}></Route>
          {/* http://localhost:3000/editHotel/id */}
          <Route path='/editHotel/:id' element={<HotelComponent />}></Route>
          {/* http://localhost:3000/korisnici */}
          <Route path='/korisnici' element={<ListKorisnikComponent />}></Route>
          {/* http://localhost:3000/korisnici/uredi/id */}
          <Route path='/korisnici/uredi/:id' element={<KorisnikComponent />}></Route>
        </Routes>
        <FooterComponent />
      </BrowserRouter>
    </>
  )
}

export default App
