import './App.css'
import { Routes, Route, Link } from 'react-router-dom'
import Home from './pages/Home'
import Register from './pages/Register'
import ManageAccount from './pages/ManageAccount'
import Login from './pages/Login'

export default function () {

  const handleLogout = () => {
    localStorage.setItem('id', '')
  }

  return <>
    <Routes>
      <Route path='/' element={<Login/>} />
      <Route path='/home' element={<Home/>}/>
      <Route path='/manageAccount' element={<ManageAccount />} />
      <Route path='/register' element={<Register />} />
    </Routes>
    <footer>
      <nav>
        <Link to="/">Login</Link>
        <Link to="/home">Home</Link>
        <Link to="/manageAccount">Manage account </Link>
        <Link to="/register">Register</Link>
        <Link to="/" onClick={handleLogout}>Logout</Link>
      </nav>
    </footer>

  </>
}
