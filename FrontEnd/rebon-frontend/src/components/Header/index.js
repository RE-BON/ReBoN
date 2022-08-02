import '../../styles/header.css';
import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import SearchModal from './SearchModal';
import HeaderModal from './HeaderModal';
import { useMediaQuery } from 'react-responsive';
import axios from 'axios';

export default function Header() {
  const isMobile = useMediaQuery({
    query: '(max-width:767px)',
  });

  const [isLogin, setIsLogin] = useState(false);
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
          setIsLogin(true);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }, []);

  return (
    <div className={`header-wrapper ${window.location.href === 'http://localhost:3000/' ? 'header-wrapper-pink' : ''}`}>
      <header>
        <Link to="/">
          <img src="/image/logo.png" alt="logo" className="logo-img" />
        </Link>
        {isLogin ? (
          <div className="header-logon">
            <div className="header-logon-modal">
              <SearchModal />
            </div>

            {isMobile ? (
              <div className="icon">
                <HeaderModal />
              </div>
            ) : (
              <Link to="/mypage" style={{ color: 'inherit', textDecoration: 'none' }}>
                <div className="profile">
                  <img src="/image/user-background.png" alt="user" className="header-modal-img" /> {userInfo.nickName} 님
                </div>
              </Link>
            )}
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
