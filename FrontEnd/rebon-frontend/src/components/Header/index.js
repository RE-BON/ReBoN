import '../../styles/header.css';
import React, { useState, useEffect } from 'react';
import { Link, useLocation } from 'react-router-dom';
import SearchModal from './SearchModal';
export default function Header() {
  let headerLocation = useLocation();
  const userName = localStorage.getItem('userName');
  //로그인인지 아닌지에 따라 헤더 상태 다름
  const [isLogin, setIsLogin] = useState(true);
  function Login(e) {
    setIsLogin(true);
  }

  const [headerState, setHeaderState] = useState({ bgColor: 'white', bottomLine: '#E9E9EA' });
  useEffect(() => {
    if (headerLocation.pathname === '/') {
      setHeaderState({ bgColor: 'transparent', bottomLine: '#ff6b6c' });
    }
  }, [headerLocation]);

  return (
    <div className="header-wrapper" style={{ backgroundColor: headerState.bgColor, borderBottomColor: headerState.bottomLine }}>
      <header>
        <Link to="/">
          <img src="/image/logo.png" alt="logo" />
        </Link>
        {isLogin ? (
          <div className="header-logon">
            <div className="header-logon-modal">
              <SearchModal />
            </div>

            <Link to="/mypage" style={{ color: 'inherit', textDecoration: 'none' }}>
              <div className="header-logon-icon">
                <img src="/image/user.png" alt="user" width="100px" />
                {userName}
              </div>
            </Link>
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
