import '../../../styles/edit.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark, faCheck } from '@fortawesome/free-solid-svg-icons';
import { useState } from 'react';
import axios from 'axios';

export default function Edit() {
  const userName = localStorage.getItem('userName');
  const [name, setName] = useState('');
  const onChangeName = (e) => {
    setName(e.target.value);
  };

  const checkNick = () => {
    axios
      .post('http://34.238.48.93:8080/api/members/nickname/check-duplicate', {
        nickname: { name },
      })
      .then(function (response) {
        // response
        console.log(response);
      })
      .catch(function (error) {
        // 오류발생시 실행
      })
      .then(function () {
        // 항상 실행
        console.log({ name });
      });
  };

  const saveData = (e) => {
    window.localStorage.setItem('userName', name);
    window.location.reload();
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
              <img src="image/user.png" alt="user-img" />
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
              setName('test');
              checkNick();
            }}
          >
            중복확인
          </div>
        </div>

        <div className="edit-info-title ">이메일</div>
        <div className="edit-info-email">duifusi@gmail.com</div>
        <div className="edit-info-marketing">마케팅 수신 동의</div>
        <div className="edit-info-notice ">이메일 수신에 동의하시면 Rebon 주식회사가 제공하는 서비스의 업데이트 및 이벤트 소식을 받을 수 있습니다.</div>

        <div className="select">
          <input type="radio" id="select" name="marcketing" />
          <label htmlFor="select">
            <FontAwesomeIcon icon={faCheck} />
          </label>
          동의 합니다.
          <span className="empty"></span>
          <input type="radio" id="select2" name="marcketing" />
          <label htmlFor="select2">
            <FontAwesomeIcon icon={faCheck} />
          </label>
          동의하지 않습니다.
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
