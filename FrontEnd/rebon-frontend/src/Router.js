import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Search from './Pages/Search';

function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Search />} />
      </Routes>
    </BrowserRouter>
  );
}

export default Router;
