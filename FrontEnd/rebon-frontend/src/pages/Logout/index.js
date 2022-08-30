import React from 'react';
import Header from '../../components/Header';
import { Link } from 'react-router-dom';
import '../../styles/logout.css';

export default function Logout() {
  return (
    <>
      <Header />
      <div className="login-wrapper">
        <div className="login-icon">
          <img src="/image/login.png" />
        </div>
        <div className="login-notice">
          로그인이 필요한 서비스 입니다.
          <div>회원이 아니시면 회원가입을 해주세요.</div>
        </div>

        <div className="button">
          <Link to="/login" style={{ color: 'inherit', textDecoration: 'none' }}>
            <div className="button-login">로그인</div>
          </Link>
          <Link to="/join" style={{ color: 'inherit', textDecoration: 'none' }}>
            <div className="button-join">회원가입</div>
          </Link>
        </div>
      </div>
    </>
  );
}
