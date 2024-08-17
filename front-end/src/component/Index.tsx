import FooterFragment from './fragment/Footer'
import NavigationFragment from './fragment/Navigation'
import { Outlet } from 'react-router-dom'

const IndexComponent = () => {
  return (
    <>
    <NavigationFragment/>
    <Outlet/>
    <FooterFragment/>
    </>
  )
}

export default IndexComponent