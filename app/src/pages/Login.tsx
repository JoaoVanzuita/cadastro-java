import { useState } from "react"
import { useNavigate } from "react-router-dom"
import Swal from "sweetalert2"
import { useApi } from "../hooks/useApi"

export default function () {
  const api = useApi()
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const navigate = useNavigate()

  const handleLogin = async () => {

    if (email && password) {
      const response = await api.signin(email, password)

      if (response.id) {

        Swal.fire(`Success`, ``, `success`)
        
        localStorage.setItem('id', response.id)
        navigate('/home')
        return
      }

      Swal.fire(`Server error`, ``, `error`)
      return
    }

    Swal.fire('Error', 'Data was not loaded correctly', 'error')
  }

  return <>
    <div className="tittle"><h1>Login</h1></div>

    <div className="form">
      <input
        type="text"
        onChange={ev => setEmail(ev.currentTarget.value)}
        name="email"
        value={email}
        placeholder="Enter your email"
      />
      <input
        type="password"
        onChange={ev => setPassword(ev.currentTarget.value)}

        name="password"
        value={password}
        placeholder="Enter your password"
      />
      <div className="action" >
        <button onClick={handleLogin}>Login</button>
      </div>
    </div>
  </>
}
