import React, { useEffect } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark, faCheck } from '@fortawesome/free-solid-svg-icons';
import { useState } from 'react';
import { IoIosArrowForward } from 'react-icons/io';
import { Link } from 'react-router-dom';
import '../../../styles/terms.css';
import axios from 'axios';
import { useLocation, useNavigate } from 'react-router';
import Alert from '@mui/material/Alert';
import Stack from '@mui/material/Stack';

export default function Terms() {
  const location = useLocation();
  const navigate = useNavigate();
  const [name, setName] = useState('홍길동');
  const [email, setEmail] = useState(location.state.email);
  const [oauthProvider, setOauthProvider] = useState(location.state.oauthProvider);
  const [alertState, setAlertState] = useState({ display: 'none', check: 'error', message: '' });

  const onChangeName = (e) => {
    setName(e.target.value);
  };

  useEffect(() => {
    console.log(oauthProvider);
  });

  const clickRegister = async () => {
    await axios({
      method: 'post',
      url: 'http://3.34.139.61:8080/api/members',
      data: {
        email: email,
        nickname: name,
        oauthProvider: oauthProvider,
        agreed: 'true',
      },
      dataType: 'application/json',
    })
      .then(function (response) {
        console.log(response);
        alert('회원 가입이 완료 되었습니다 :)');
        window.sessionStorage.setItem('token', response.data.token);
        navigate('/');
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  const checkNick = () => {
    axios
      .post('http://3.34.139.61:8080/api/members/nickname/check-duplicate', {
        nickname: name,
      })
      .then(function (response) {
        // -- 이 200일 경우
        console.log(response);
        setAlertState({ display: 'block', check: 'success', message: '사용 가능한 아이디 입니다' });
      })
      .catch(function (error) {
        // 오류발생시 실행 -- 이 400일 경우, alert error 출력, 닉네임 input 공백,
        console.log(error);
        setName(name);
        setAlertState({ display: 'block', check: 'error', message: '이미 있는 아이디 입니다' });
      })
      .then(function () {
        // 항상 실행
      });
  };

  const allClick= () => {
    const is_checked = document.getElementById('agreement-select').checked;
    if(is_checked) {
      document.getElementById('agreement-select1').checked = true;
      document.getElementById('agreement-select2').checked = true;
      document.getElementById('agreement-select3').checked = true;
    }
    else{
      document.getElementById('agreement-select1').checked = false;
      document.getElementById('agreement-select2').checked = false;
      document.getElementById('agreement-select3').checked = false;
    }
  }

  return (
    <div className="terms-wrapper">
      <div className="termsBox">
        <div className="terms-title">마지막 단계입니다!</div>
        <div className="terms-container">
          <div className="nickname">
            닉네임
            <div className="nickname-bar">
              <input className="nickname-input" value={name} placeholder="" onChange={onChangeName}></input>
              <div
                className="nickname-btn"
                onClick={() => {
                  checkNick();
                }}
              >
                중복확인
              </div>
            </div>
            <div className="nickname-count">1/10</div>
            <div style={{ display: alertState.display }}>
              <Stack sx={{ width: '100%' }} spacing={2}>
                <Alert severity={alertState.check}>{alertState.message}</Alert>
              </Stack>
            </div>
          </div>

          <div>
            <div className="agreement-select">
              <input type="checkbox" id="agreement-select" name="marcketing" onClick={allClick}/>
              <label for="agreement-select" >
                <FontAwesomeIcon icon={faCheck} />
              </label>
              전체동의
            </div>

            <hr />
            <div className="agreement-select">
              <input type="checkbox" id="agreement-select1" name="marcketing" />
              <label for="agreement-select1">
                <FontAwesomeIcon icon={faCheck} />
              </label>
              서비스 이용약관(필수)
              <Link to="/termsModal" style={{ marginLeft: '1em', color: 'inherit', textDecoration: 'none' }}>
                <IoIosArrowForward />
              </Link>
            </div>

            <div className="agreement-select">
              <input type="checkbox" id="agreement-select2" name="marcketing" />
              <label for="agreement-select2">
                <FontAwesomeIcon icon={faCheck} />
              </label>
              개인정보 취급방식(필수)
              <Link to="/privacyModal" style={{ color: 'inherit', textDecoration: 'none' }}>
                <IoIosArrowForward />
              </Link>
            </div>

            <div className="agreement-select">
              <input type="checkbox" id="agreement-select3" name="marcketing" />
              <label for="agreement-select3">
                <FontAwesomeIcon icon={faCheck} />
              </label>
              마케팅 수신동의(선택)
              <Link to="/marketingModal" style={{ marginLeft: '1em', color: 'inherit', textDecoration: 'none' }}>
                <IoIosArrowForward />
              </Link>
            </div>
            <button className="terms-btn" onClick={clickRegister}>
              ReBON 시작하기
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
