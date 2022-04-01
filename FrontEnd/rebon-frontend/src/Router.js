import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Detail from './pages/Detail';
import Main from './pages/Main';
import Search from './pages/Search';
import Post from './pages/Post';
import Footprint from './pages/Footprint';
import Mypage from './pages/Mypage';
import ReviewDropdown from './pages/Detail/Review/ReviewDropdown';

function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/detail" element={<Detail />} />
        <Route path="/search" element={<Search />} />
        <Route path="/main" element={<Main />} />
        <Route path="/post" element={<Post />} />
        <Route path="/footprint" element={<Footprint />} />
        <Route path="/mypage" element={<Mypage />} />
        <Route path="/reviewdropdown" element={<ReviewDropdown />} />
      </Routes>
    </BrowserRouter>
  );
}

export default Router;
