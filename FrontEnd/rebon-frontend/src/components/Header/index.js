import '../../styles/header.css';
import React, { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import SearchModal from './SearchModal';
export default function Header() {
  const [isLogin, setIsLogin] = useState(false);
  function Login(e) {
    setIsLogin(true);
  }
  return (
    <div className="header-wrapper">
      <header>
        <div className="header-logo">dd</div>
        {isLogin ? (
          <div className="header-log-on">
            <SearchModal />
            <div className="header-log-on-profile">
              <div>
                <FontAwesomeIcon icon={faUser} className="userProfile" />
              </div>
              홍길동
            </div>
          </div>
        ) : (
          <div onClick={Login} className="header-log-off">
            <span>로그인</span>
            <span>회원가입</span>
          </div>
        )}
      </header>
    </div>
  );
}
