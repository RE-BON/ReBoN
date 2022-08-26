import '../../../styles/edit.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCheck } from '@fortawesome/free-solid-svg-icons';
import { useState, useEffect } from 'react';
import axios from 'axios';
import * as React from 'react';
import Alert from '@mui/material/Alert';
import Stack from '@mui/material/Stack';

export default function Edit() {
  const [alertState, setAlertState] = useState({ display: 'none', check: 'error', message: '' });
  const [token, setToken] = useState(window.sessionStorage.getItem('token'));
  const [userInfo, setUserInfo] = useState({});

  //로그인 정보 받아오기
  useEffect(() => {
    const config = {
      headers: { Authorization: `Bearer ${token}` },
    };
    if (token) {
      axios
        .get('http://3.34.139.61:8080/api/members', config)
        .then((response) => {
          setUserInfo(response.data);
          setName(response.data.nickName);
          setUserAgreed(response.data.agreed);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }, []);
  //이름 설정
  const [name, setName] = useState(userInfo.nickName);
  const onChangeName = (e) => {
    setName(e.target.value);
  };

  //닉네임 확인하기
  const checkNick = () => {
    const config = {
      headers: { Authorization: `Bearer ${token}` },
    };
    axios
      .post(
        'http://3.34.139.61:8080/api/members/nickname/check-duplicate',
        {
          nickname: name,
        },
        config
      )
      .then(function (response) {
        // -- 이 200일 경우
        setAlertState({ display: 'block', check: 'success', message: '사용 가능한 아이디 입니다' });
      })
      .catch(function (error) {
        // 오류발생시 실행 -- 이 400일 경우, alert error 출력, 닉네임 input 공백,
        setName(userInfo);
        setAlertState({ display: 'block', check: 'error', message: '이미 있는 아이디 입니다' });
      });
  };

  // 동의 여부
  const [userAgreed, setUserAgreed] = useState(userInfo.agreed);
  const onChangeAgree = (e) => {
    setUserAgreed(!userAgreed);
  };

  const saveData = () => {
    const config = {
      headers: { Authorization: `Bearer ${token}` },
    };
    console.log(name, userAgreed);
    axios
      .patch(
        'http://3.34.139.61:8080/api/members',
        {
          nickname: name,
          agreed: userAgreed,
        },
        config
      )
      .then(function (response) {
        // -- 이 200일 경우
        setAlertState({ display: 'block', check: 'success', message: '사용 가능한 아이디 입니다' });
      })
      .catch(function (error) {
        // 오류발생시 실행 -- 이 400일 경우, alert error 출력, 닉네임 input 공백,
        setName(userInfo);
        setAlertState({ display: 'block', check: 'error', message: '이미 있는 아이디 입니다' });
      })
      .then(window.location.reload());
  };

  return (
    <div className="edit-container">
      <div className="edit-title">
        <div>회원정보 수정</div>
      </div>

      <div className="edit-info">
        <div className="edit-info-icon">
          <div>
            <span className="icon-user">
              <img src="image/user-background.png" alt="user-img" />
            </span>
            <span className="icon-camera">
              <img src="image/camera.png" alt="camera-img" />
            </span>
          </div>
        </div>
        <div className="edit-info-title">닉네임</div>
        <div className="edit-info-name">
          <input className="name-input" value={name} placeholder="한글로 공백없이 입력해주세요." onChange={onChangeName}></input>
          <div
            className="name-button"
            onClick={() => {
              checkNick();
            }}
          >
            중복확인
          </div>
        </div>
        <div style={{ display: alertState.display }}>
          <Stack sx={{ width: '100%' }} spacing={2}>
            <Alert severity={alertState.check}>{alertState.message}</Alert>
          </Stack>
        </div>
        <div className="edit-info-title ">이메일</div>
        <div className="edit-info-email">{userInfo.email}</div>
        <div className="edit-info-marketing">마케팅 수신 동의</div>
        <div className="edit-info-notice ">이메일 수신에 동의하시면 Rebon 주식회사가 제공하는 서비스의 업데이트 및 이벤트 소식을 받을 수 있습니다.</div>

        <div className="select">
          <input type="radio" id="select" name="marcketing" checked={userAgreed} onChange={onChangeAgree} />
          <label htmlFor="select">
            <FontAwesomeIcon icon={faCheck} />
          </label>
          <span style={{ paddingRight: '0.5rem' }}></span>
          <span className="agree-discribe">동의 합니다.</span>
          <span className="empty"></span>
          <input type="radio" id="select2" name="marcketing" checked={!userAgreed} onChange={onChangeAgree} />
          <label htmlFor="select2">
            <FontAwesomeIcon icon={faCheck} />
          </label>
          <span style={{ paddingRight: '0.5rem' }}></span>
          <span className="agree-discribe">동의하지 않습니다.</span>
        </div>

        <div className="edit-info-submit">
          <div className="cancel">취소</div>
          <div className="finished" onClick={saveData}>
            수정완료
          </div>
        </div>
      </div>
    </div>
  );
}
