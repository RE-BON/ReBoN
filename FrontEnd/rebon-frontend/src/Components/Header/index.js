import styled from 'styled-components';
import SearchModal from './SearchModal';
import React, { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser } from '@fortawesome/free-solid-svg-icons';

function Header() {
  const [isLogin, setIsLogin] = useState(false);

  function Login(e) {
    setIsLogin(true);
  }

  return (
    <Wrapper>
      <HeaderStyle>
        <Logo />
        {isLogin ? (
          <LogOn>
            <SearchModal />
            <Profile>
              <div>
                <FontAwesomeIcon icon={faUser} className="userProfile" />
              </div>
              홍길동
            </Profile>
          </LogOn>
        ) : (
          <LogOff onClick={Login}>
            <span>로그인</span>
            <span>회원가입</span>
          </LogOff>
        )}
      </HeaderStyle>
    </Wrapper>
  );
}

const Wrapper = styled.div`
  display: flex;
  position: fixed;
  align-items: center;
  justify-content: center;
  height: 100px;
  width: 100%;
  top: 10px;
  z-index: 5;
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
`;

const HeaderStyle = styled.header`
  width: 60vw;
  height: 20vh;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const LogOn = styled.div`
  display: flex;
  font-weight: 700;
`;

const LogOff = styled.div`
  font-weight: 700;
  span {
    margin-left: 50px;
  }
`;

const Logo = styled.div`
  //이건 따로 컴포넌트를 만들어야 할 것이에요..
  width: 174px;
  height: 74px;
  margin-right: 150px;
  background-color: rgba(0, 0, 0, 0.3);
`;

const Profile = styled.div`
  //이건 따로 컴포넌트를 만들어야 할 것이에요..
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: 700;
  div {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 35px;
    height: 35px;
    margin-right: 5px;
    margin-left: 40px;
    border-radius: 50%;
    background-color: rgba(0, 0, 0, 0.2);
  }
  .userProfile {
    color: rgba(0, 0, 0, 0.5);
  }
`;

export default Header;
