import React, { useState } from 'react';
import { Row, Col } from 'react-bootstrap';
import { ImCog } from 'react-icons/im';
import { RiFootprintLine } from 'react-icons/ri';
import { FiHeart } from 'react-icons/fi';
import { IoIosArrowForward } from 'react-icons/io';

import { Link } from 'react-router-dom';
import { Footprint } from '../Footprint';

export default function SideMenu() {
  const [view, setView] = useState(0);

  const FootprintClick = () => {
    console.log('+1');
    setView((view = 0));
  };

  return (
    <div className="sideMenu-wrapper">
      <div className="sideMenu-intro">
        안녕하세요.
        <br />
        <span className="name">홍길동</span>님{' '}
        <Link to="#">
          <ImCog color="black" size="18" />
        </Link>
      </div>

      <Row className="sidebtn-wrapper align-middle" onClick={FootprintClick}>
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
      <Row className="sideMenu-bottom">
        <Link to="#" style={{ textDecoration: 'none', color: 'black' }}>
          <div className="mb-2">로그아웃</div>
        </Link>
        <Link to="#" style={{ textDecoration: 'none', color: 'black' }}>
          <div>회원탈퇴</div>
        </Link>
      </Row>
    </div>
  );
}
