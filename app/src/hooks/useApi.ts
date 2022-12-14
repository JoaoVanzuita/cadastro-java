import { User } from "../types/User"

export const useApi = () => ({

  signin: async (email: string, password: string) => {

    const response = await fetch(`api/user/login`, {
      method: 'POST',
      headers: {
        'Content-Type':
          'application/json'
      },
      body: JSON.stringify({ email, password })
    })

    return await response.json()
  },
  getById: async () => {

    const id = localStorage.getItem('id')
    const response = await fetch(`api/user/${id}`, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' }
    })

    return await response.json()
  },
  create: async (user: User) => {

    const { name, email, password } = user
    const response = await fetch(`api/user`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ name, email, password })
    })

    return await response.json()
  },
  update: async (user: User) => {

    const id = localStorage.getItem('id')
    const { name, email, password } = user
    const response = await fetch(`api/user/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ name, email, password })
    })

    return await response.json()
  },
  delete: async (user: User) => {

    const id = user.idUser
    const response = await fetch(`api/user/${id}`, {
      method: 'DELETE',
      headers: { 'Content-Type': 'application/json' }
    })

    return await response.json()
  }
})
