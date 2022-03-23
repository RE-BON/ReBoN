import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Detail from './pages/Detail';
import Main from './pages/Main';
import Search from './pages/Search';
import Footprint from './pages/Footprint';

function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/detail" element={<Detail />} />
        <Route path="/search" element={<Search />} />
        <Route path="/main" element={<Main />} />
        <Route path="/footprint" element={<Footprint />} />
      </Routes>
    </BrowserRouter>
  );
}

export default Router;
