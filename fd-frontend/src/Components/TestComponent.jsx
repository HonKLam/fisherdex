import { fetchData, postData } from '../api/api'
import { useQuery, useMutation } from 'react-query'

export default function TestComponent() {
    // GET REQUESTS
    const { data, isLoading, isError } = useQuery('data', () =>
        fetchData('/testuser')
    )

    // POST REQUEST
    const { mutate } = useMutation((data) => postData('/user', data))

    // Das könnte auch ein Form sein was handleSubmit() macht
    const handleClick = () => {
        const data = {
            name: 'Marc',
            age: 23,
            location: 'Leipzig',
        }
        // Der Eigentliche POST REQUEST wird hier ausgeführt
        mutate(data)
    }

    if (isLoading) return <div>Laden...</div>
    if (isError) return <div>Ein Fehler ist aufgetreten!</div>

    return (
        <div>
            {data.map((obj) => {
                return <div key={obj.name}>{obj.name}</div>
            })}
            <button onClick={handleClick}>Click Me</button>
        </div>
    )
}
