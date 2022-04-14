import { Row, Col } from 'react-bootstrap';
import { ImCog } from 'react-icons/im';
import { RiFootprintLine } from 'react-icons/ri';
import { FiHeart } from 'react-icons/fi';
import { IoIosArrowForward } from 'react-icons/io';
import { Link } from 'react-router-dom';
import { Route, Routes } from 'react-router-dom';
import { useState } from 'react';

import Bookmark from './Bookmark';
import Footprint from './Footprint';
import Edit from './Edit';
import Header from '../../components/Header';
import LogoutModal from './LogoutModal';
import Withdrawal from './Withdrawal';
import '../../styles/mypage.css';

export default function Mypage() {
  const userName = localStorage.getItem('userName');

  const [clickedTab, setClickedTab] = useState(1);

  return (
    <div className="my-wrapper">
      <Header />
      <div className="mypage-wrapper">
        <div className="mypage-tab">
          <div className="sideMenu-wrapper">
            <div className="sideMenu-intro">
              안녕하세요.
              <br />
              <span className="name">{userName}</span>님{' '}
              <Link to="" onClick={() => setClickedTab(1)}>
                <ImCog className={clickedTab === 1 ? 'tab-click-active' : 'tab-click-stay'} color="black" size="18" />
              </Link>
            </div>

            <Link to="/mypage/footprint" onClick={() => setClickedTab(2)} style={{ color: 'inherit', textDecoration: 'none' }}>
              <Row className="sidebtn-wrapper">
                <Col md={2} className={clickedTab === 2 ? 'tab-click-active' : 'tab-click-stay'}>
                  <div className="sidebtn-icon">
                    {console.log(clickedTab)}
                    <RiFootprintLine size="24" />
                  </div>
                </Col>
                <Col md={8} className={clickedTab === 2 ? 'tab-click-active' : 'tab-click-stay'}>
                  <div className="btn-name">발자국</div>
                  <div className="sub-txt">리뷰를 확인할 수 있어요.</div>
                </Col>
                <Col md={1} className={clickedTab === 2 ? 'tab-click-active' : 'tab-click-stay'}>
                  <div>
                    <IoIosArrowForward />
                  </div>
                </Col>
              </Row>
            </Link>

            <Link to="/mypage/bookmark" onClick={() => setClickedTab(3)} style={{ color: 'inherit', textDecoration: 'none' }}>
              <Row className="sidebtn-wrapper">
                <Col md={2} className={clickedTab === 3 ? 'tab-click-active' : 'tab-click-stay'}>
                  <div className="sidebtn-icon">
                    <FiHeart md={8} size="22" />
                  </div>
                </Col>
                <Col md={8} className={clickedTab === 3 ? 'tab-click-active' : 'tab-click-stay'}>
                  <div className="btn-name">찜</div>
                  <div className="sub-txt">찜한 목록을 확인할 수 있어요.</div>
                </Col>
                <Col md={2} className={clickedTab === 3 ? 'tab-click-active' : 'tab-click-stay'}>
                  <div>
                    <IoIosArrowForward />
                  </div>
                </Col>
              </Row>
            </Link>

            <Row className="sideMenu-bottom">
              <div className="mb-2">
                <LogoutModal />
              </div>
              <Link to="withdrawal" onClick={() => setClickedTab(4)} style={{ color: 'inherit', textDecoration: 'none' }}>
                <div className={clickedTab === 4 ? 'tab-click-active' : 'tab-click-stay'}>회원탈퇴</div>
              </Link>
            </Row>
          </div>
        </div>

        <div className="mypage-content">
          <Routes>
            <Route path="" element={<Edit />} />
            <Route path="footprint" element={<Footprint />} />
            <Route path="bookmark" element={<Bookmark />} />
            <Route path="withdrawal" element={<Withdrawal />} />
          </Routes>
        </div>
      </div>
    </div>
  );
}
