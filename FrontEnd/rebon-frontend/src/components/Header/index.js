import '../../styles/header.css';
import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import SearchModal from './SearchModal';
import axios from 'axios';

export default function Header() {
  const [isLogin, setIsLogin] = useState(true);
  const [token, setToken] = useState(window.sessionStorage.getItem('token'));

  useEffect(() => {
    const config = {
      headers: { Authorization: `Bearer ${token}` },
    };
    if (token) {
      axios
        .get('http://3.34.139.61:8080/api/members', config)
        .then((response) => {
          setToken(response.data);
          setIsLogin(true);
        })
        .catch((error) => {
          console.log(error);
          // setIsLogin(false);
        });
    }
  }, [token]);

  let headerBorder = document.querySelector('.header-wrapper');

  // if (window.location.href == 'http://localhost:3000') {
  //   console.log('header changed');
  //   headerBorder.classList.remove('header-wrapper-normal');
  //   headerBorder.classList.add('header-wrapper-home');
  // } else {
  //   headerBorder.classList.remove('header-wrapper-home');
  //   headerBorder.classList.add('header-wrapper-normal');
  // }
  console.log('Header 새로고침 입니다');

  return (
    <div className="header-wrapper">
      <header>
        <Link to="/">
          <img src="/image/logo.png" alt="logo" className="logo-img" />
        </Link>
        {isLogin ? (
          <div className="header-logon">
            <div className="header-logon-modal">
              <SearchModal />
            </div>

            <Link to="/mypage" style={{ color: 'inherit', textDecoration: 'none' }}>
              <div className="header-logon-icon">
                <img src="/image/user.png" alt="user" />
              </div>
            </Link>
            {/* <span className="header-logon-name">{token.nickName}</span> */}
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
