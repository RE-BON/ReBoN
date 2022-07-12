import React from 'react';
import '../../styles/join.css';
import { Link } from 'react-router-dom';

import SNSButtons from './LoginButton';

export default function Login() {
  return (
    <div className="join-wrapper">
      <div>
          <img className="logo" alt="googleLogo" src="image/logo.png" hidden/>
        </div>
      <div className="centerBox">
        <div class="join-title">
          빠르고 쉽게
          <br /> ReBON에 로그인하세요!
          <br />
          <span className="subtitle">오늘도 다양한 리뷰들이 기다리고 있어요:)</span>
        </div>
        <SNSButtons />
        <hr className="divider" />
        <div>
          회원이 아니신가요? <br />
          회원가입하고 위치별 맛집, 숙소를 찾아보세요.
        </div>
        <Link to="/join" style={{ color: 'inherit', textDecoration: 'none' }}>
          <button className="join-bottom-btn">회원가입</button>
        </Link>
      </div>
    </div>
  );
}
