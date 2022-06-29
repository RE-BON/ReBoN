import '../../styles/header.css';
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import SearchModal from './SearchModal';
export default function Header() {
  const userName = localStorage.getItem('userName');
  //로그인인지 아닌지에 따라 헤더 상태 다름
  const [isLogin, setIsLogin] = useState(true);
  function Login(e) {
    setIsLogin(true);
  }

  return (
    <div className="header-wrapper">
      <header>
        <Link to="/">
          <img src="image/logo.png" />
        </Link>
        {isLogin ? (
          <div className="header-logon">
            <div className="header-logon-modal">
              <SearchModal />
            </div>
            <div className="header-logon-icon">
              <img src="image/user.png" />
            </div>
            <span>{userName}</span>
          </div>
        ) : (
          <div className="header-logoff">
            <Link to="/login" style={{ color: 'inherit', textDecoration: 'none' }}>
              <div>로그인</div>
            </Link>
            <Link to="/join" style={{ color: 'inherit', textDecoration: 'none' }}>
              <div> 회원가입</div>
            </Link>
          </div>
        )}
      </header>
    </div>
  );
}
