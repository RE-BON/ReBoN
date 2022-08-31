import { Row, Col } from 'react-bootstrap';
import { ImCog } from 'react-icons/im';
import { RiFootprintLine } from 'react-icons/ri';
import { FiHeart } from 'react-icons/fi';
import { IoIosArrowForward } from 'react-icons/io';
import { Link } from 'react-router-dom';
import { Route, Routes } from 'react-router-dom';
import { useState, useEffect } from 'react';

import Bookmark from './Bookmark';
import Footprint from './Footprint';
import Edit from './Edit';
import Header from '../../components/Header';
import LogoutModal from './LogoutModal';
import Withdrawal from './Withdrawal';
import '../../styles/mypage.css';
import { useMediaQuery } from 'react-responsive';
import axios from 'axios';

export default function Mypage() {
  const [clickedTab, setClickedTab] = useState(1);
  const [token, setToken] = useState(window.sessionStorage.getItem('token'));
  const [userInfo, setUserInfo] = useState({});

  useEffect(() => {
    const config = {
      headers: { Authorization: `Bearer ${token}` },
    };
    if (token) {
      axios
        .get('http://3.34.139.61:8080/api/members', config)
        .then((response) => {
          setUserInfo(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }, []);
  const isMobile = useMediaQuery({
    query: '(max-width:767px)',
  });

  return (
    <>
      {isMobile ? (
        <div className="mb-my-wrapper">
          <Header />
          <div className="mb-mypage-wrapper">
            <div className="mb-sideMenu-intro">
              <span className="mb-hello">안녕하세요.</span>
              <br />
              <span className="mb-name">{userInfo.nickName}</span>님
              <Link to="" onClick={() => setClickedTab(1)}>
                <ImCog color="black" size="18" className="mb-setting-icon" />
              </Link>
            </div>
            {window.location.pathname === '/mypage/withdrawal' ? null : (
              <div className="mb-mypage-tab">
                <div className={clickedTab === 2 ? 'tab-click-active' : 'tab-click-stay'}>
                  <Link to="/mypage/footprint" onClick={() => setClickedTab(2)} style={{ color: 'inherit', textDecoration: 'none' }}>
                    <div className={clickedTab === 2 ? 'btn-click-active' : 'btn-click-stay'}>발자국</div>
                  </Link>
                </div>
                <div className="mb-border"></div>
                <div className={clickedTab === 3 ? 'tab-click-active' : 'tab-click-stay'}>
                  <Link to="/mypage/bookmark" onClick={() => setClickedTab(3)} style={{ color: 'inherit', textDecoration: 'none' }}>
                    <div className={clickedTab === 3 ? 'btn-click-active' : 'btn-click-stay'}>찜</div>
                  </Link>
                </div>
              </div>
            )}

            <div className="mb-mypage-content">
              <Routes>
                <Route path="" element={<Edit />} />
                <Route path="footprint" element={<Footprint />} />
                <Route path="bookmark" element={<Bookmark />} />
                <Route path="withdrawal" element={<Withdrawal />} />
              </Routes>
            </div>
          </div>
        </div>
      ) : (
        <div className="my-wrapper">
          <Header />
          <div className="mypage-wrapper">
            <div className="mypage-tab">
              <div className="sideMenu-wrapper">
                <div className="sideMenu-intro">
                  안녕하세요.
                  <br />
                  <span className="name">{userInfo.nickName}</span>님{' '}
                  <Link to="" onClick={() => setClickedTab(1)}>
                    <ImCog className={clickedTab === 1 ? 'tab-click-active' : 'tab-click-stay'} color="black" size="18" />
                  </Link>
                </div>

                <Link to="/mypage/footprint" onClick={() => setClickedTab(2)} style={{ color: 'inherit', textDecoration: 'none' }}>
                  <Row className="sidebtn-wrapper">
                    <Col md={3} className={clickedTab === 2 ? 'tab-click-active' : 'tab-click-stay'}>
                      <div className="sidebtn-icon">
                        <RiFootprintLine size="24" />
                      </div>
                    </Col>
                    <Col md={7} className={clickedTab === 2 ? 'tab-click-active' : 'tab-click-stay'}>
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
                    <Col md={3} className={clickedTab === 3 ? 'tab-click-active' : 'tab-click-stay'}>
                      <div className="sidebtn-icon">
                        <FiHeart md={7} size="22" />
                      </div>
                    </Col>
                    <Col md={7} className={clickedTab === 3 ? 'tab-click-active' : 'tab-click-stay'}>
                      <div className="btn-name">찜</div>
                      <div className="sub-txt">찜한 목록을 확인할 수 있어요.</div>
                    </Col>
                    <Col md={1} className={clickedTab === 3 ? 'tab-click-active' : 'tab-click-stay'}>
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
                    <div className="withdrawal-click">회원탈퇴</div>
                  </Link>
                </Row>
              </div>
            </div>

            <div>
              <Routes>
                <Route path="" element={<Edit />} />
                <Route path="footprint" element={<Footprint />} />
                <Route path="bookmark" element={<Bookmark />} />
                <Route path="withdrawal" element={<Withdrawal />} />
              </Routes>
            </div>
          </div>
        </div>
      )}
    </>
  );
}
