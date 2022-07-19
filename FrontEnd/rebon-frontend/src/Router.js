import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Detail from './pages/Detail';
import Main from './pages/Main';
import Search from './pages/Search';
import Post from './pages/Post';
import Modify from './pages/Modify';
import Mypage from './pages/Mypage';
import ReviewDropdown from './pages/Detail/Review/ReviewDropdown';
import Join from './pages/Join';
import Login from './pages/Login';
// import Register from './pages/Join/Register';
import Terms from './pages/Join/Terms';
import Logout from './pages/Logout';
// import Terms from './pages/Terms';
import Loding from './pages/Login/Loading';
/*추후변경필요*/
import TermsMarketing from './pages/Join/Terms/marketingModal';
import TermsPrivacy from './pages/Join/Terms/privacyModal';
import TermsModal from './pages/Join/Terms/termsModal';

function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/detail/:id" element={<Detail />} />
        <Route path="/main" element={<Main />} />
        <Route path="/post" element={<Post />} />
        <Route path="/post" element={<Modify />} />
        <Route path="/reviewdropdown" element={<ReviewDropdown />} />
        <Route path="/login" element={<Login />} />
        <Route path="/mypage/*" element={<Mypage />} />
        <Route path="/join" element={<Join />} />
        {/* <Route path="/join/register" element={<Register />} /> */}
        <Route path="/logout" element={<Logout />} />
        <Route path="/login" element={<Login />} />
        <Route path="/join/register" element={<Terms />} />
        <Route path="/termsModal" element={<TermsModal />} />
        <Route path="/" element={<Search />} />
        <Route path="/loading" element={<Loding />} />
      </Routes>
    </BrowserRouter>
  );
}

export default Router;
