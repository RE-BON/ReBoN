import { Row, Col } from 'react-bootstrap';
import { ImCog } from 'react-icons/im';
import { RiFootprintLine } from 'react-icons/ri';
import { FiHeart } from 'react-icons/fi';
import { IoIosArrowForward } from 'react-icons/io';

import { Link } from 'react-router-dom';
import { Route, Routes } from 'react-router-dom';

import Bookmark from './Bookmark';
import Footprint from './Footprint';
import Edit from './Edit';
import Header from '../../components/Header';

import '../../styles/mypage.css';
import '../../styles/myedit.css';

export default function Mypage() {
  return (
    <div className="my-wrapper">
      <Header />
      <div className="mypage-wrapper">
        <div className="mypage-tab">
          {/* sideMaenu */}
          <div className="sideMenu-wrapper">
            <div className="sideMenu-intro">
              안녕하세요.
              <br />
              <span className="name">홍길동</span>님{' '}
              <Link to="#">
                {/* 여기서 페이지 이동 어디로? */}
                <ImCog color="black" size="18" />
              </Link>
            </div>

            <Link to="/mypage/footprint">
              <Row className="sidebtn-wrapper align-middle">
                <Col md={2}>
                  <div className="sidebtn-icon">
                    <RiFootprintLine size="24" color="#898C8F" />
                  </div>
                </Col>
                <Col md={8}>
                  <div className="btn-name">발자국</div>
                  <div className="sub-txt">리뷰를 확인할 수 있어요.</div>
                </Col>
                <Col md={2}>
                  <div>
                    <IoIosArrowForward color="#A5A9AB" />
                  </div>
                </Col>
              </Row>
            </Link>

            <Link to="/mypage/bookmark">
              <Row className="sidebtn-wrapper">
                <Col md={2}>
                  <div className="sidebtn-icon">
                    <FiHeart size="22" color="#898C8F" />
                  </div>
                </Col>
                <Col md={8}>
                  <div className="btn-name">찜</div>
                  <div className="sub-txt">찜한 목록을 확인할 수 있어요.</div>
                </Col>
                <Col md={2}>
                  <div>
                    <IoIosArrowForward color="#A5A9AB" />
                  </div>
                </Col>
              </Row>
            </Link>

            <Row className="sideMenu-bottom">
              <div className="mb-2">로그아웃</div>
              <div>회원탈퇴</div>
            </Row>
          </div>
        </div>

        <div className="myedit-content-wrapper">
          <Routes>
            <Route path="/" element={<Edit />} />
            <Route path="footprint" element={<Footprint />} />
            <Route path="bookmark" element={<Bookmark />} />
          </Routes>
        </div>
      </div>
    </div>
  );
}
