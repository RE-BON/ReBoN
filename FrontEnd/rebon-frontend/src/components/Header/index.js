import '../../styles/header.css';
import React, { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCircleUser } from '@fortawesome/free-solid-svg-icons';
import SearchModal from './SearchModal';
export default function Header() {
  const [isLogin, setIsLogin] = useState(false);
  function Login(e) {
    setIsLogin(true);
  }
  return (
    <div className="header-wrapper">
      <header>
        <div className="header-logo"></div>
        {isLogin ? (
          <div className="header-logon">
            <div className="header-logon-modal">
              <SearchModal />
            </div>
            <div className="header-logon-icon">
              <FontAwesomeIcon icon={faCircleUser} size="2x" color="#BDBDBD" />
            </div>
            <span>홍길동</span>
          </div>
        ) : (
          <div onClick={Login} className="header-logoff">
            <span>로그인</span>
            <span>회원가입</span>
          </div>
        )}
      </header>
    </div>
  );
}
