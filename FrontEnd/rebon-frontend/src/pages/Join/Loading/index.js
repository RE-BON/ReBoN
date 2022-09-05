import React, { useEffect } from 'react';
import styled from 'styled-components';
import Spinner from '../../../assets/spinner.gif';
import axios from 'axios';
import { useLocation, useNavigate } from 'react-router';

export default function Loading() {
  const Background = styled.div`
    position: absolute;
    width: 100vw;
    height: 100vh;
    top: 0;
    left: 0;
    background: #ffffffb7;
    z-index: 999;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
  `;

  const LoadingText = styled.div`
    font: 1rem 'Noto Sans KR';
    text-align: center;
  `;

  const params = new URLSearchParams(window.location.search);
  const location = useLocation();
  const navigate = useNavigate();

  useEffect(() => {
    const code = params.get('code');
    console.log(code);
    console.log(location);

    if (code != null) {
      console.log('로그인 후');
      axios
        .get(`http://3.34.139.61:8080/api/auth/naver/login/token?code=${code}`)
        .then((response) => {
          window.sessionStorage.setItem('token', response.data.token);
          navigate('/');
        })
        .catch((error) => {
          if (error.response.data.email != null) {
            navigate('/join/register', { state: { email: error.response.data.email, oauthProvider: 'naver' } });
          }
        });
    }
  }, []);

  return (
    <>
      <Background>
        <LoadingText>잠시만 기다려 주세요.</LoadingText>
        <img src={Spinner} alt="로딩중" width="5%" />
      </Background>
    </>
  );
}
