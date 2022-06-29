import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Detail from './pages/Detail';
import Main from './pages/Main';
import Search from './pages/Search';
import Post from './pages/Post';
import Mypage from './pages/Mypage';
import ReviewDropdown from './pages/Detail/Review/ReviewDropdown';
import Join from './pages/Join';
import Login from './pages/Login';
import Register from './pages/Join/Register';
import Logout from './pages/Logout';
import Terms from './pages/Terms';
/*추후변경필요*/
import TermsMarketing from './pages/Terms/marketingModal';
import TermsPrivacy from './pages/Terms/privacyModal';
import TermsModal from './pages/Terms/termsModal';

function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/detail" element={<Detail />} />
        <Route path="/main" element={<Main />} />
        <Route path="/post" element={<Post />} />
        <Route path="/reviewdropdown" element={<ReviewDropdown />} />
        <Route path="/login" element={<Login />} />
        <Route path="/mypage/*" element={<Mypage />} />
        <Route path="/join" element={<Join />} />
        <Route path="/join/register" element={<Register />} />
        <Route path="/logout" element={<Logout />} />
        <Route path="/terms" element={<Terms />} />
        <Route path="/termsModal" element={<TermsModal />} />
        <Route path="/marketing" element={<TermsMarketing />} />
        <Route path="privacy" element={<TermsPrivacy />} />
        <Route path="/" element={<Search />} />
      </Routes>
    </BrowserRouter>
  );
}

export default Router;
