import { useState } from "react"
import { useNavigate } from "react-router-dom"
import Swal from "sweetalert2"
import { useApi } from "../hooks/useApi"
import { User } from "../types/User"


export default function () {
  const api = useApi()
  const [userData, setUserData] = useState<User>()
  const navigate = useNavigate()

  const handleLogin = async () => {

    if (userData) {
      const response = await api.delete(userData)

      if (response.status == 200) {

        Swal.fire(`Success`, ``, `success`)
        return
      }

      Swal.fire(`Server error`, `Status: ${response.status}`, `error`)
      return
    }

    Swal.fire('Error', 'Data was not loaded correctly', 'error')
  }

  return <>
    <div className="tittle"><h1>Login</h1></div>

    <div className="form">
      <input
        type="text"
        onChange={ev => setUserData({ ...userData!, email: ev.currentTarget.value })}
        name="email"
        value={userData?.email}
        placeholder="Enter your email"
      />
      <input
        type="password"
        onChange={ev => setUserData({ ...userData!, password: ev.currentTarget.value })}

        name="password"
        value={userData?.password}
        placeholder="Enter your password"
      />
      <div className="action" >
        <button onClick={handleLogin}>Login</button>
      </div>
    </div>
  </>
}
