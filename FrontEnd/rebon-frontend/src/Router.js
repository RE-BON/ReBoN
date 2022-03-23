import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Detail from './pages/Detail';
import Main from './pages/Main';
import Search from './pages/Search';
import Mypage from './pages/Mypage';

function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/detail" element={<Detail />} />
        <Route path="/search" element={<Search />} />
        <Route path="/main" element={<Main />} />
        <Route path="/mypage" element={<Mypage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default Router;
