import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Detail from './pages/Detail';

function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/detail" element={<Detail />} />
      </Routes>
    </BrowserRouter>
  );
}

export default Router;
