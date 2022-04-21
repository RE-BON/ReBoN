import '../../../styles/edit.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark, faCheck } from '@fortawesome/free-solid-svg-icons';
import { useState } from 'react';

export default function Edit() {
  const userName = localStorage.getItem('userName');
  const [name, setName] = useState(userName);
  const onChangeName = (e) => {
    setName(e.target.value);
  };

  const saveData = (e) => {
    window.localStorage.setItem('userName', name);
    window.location.reload();
  };

  return (
    <div>
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
          <div className="name-button">
            <FontAwesomeIcon
              icon={faXmark}
              onClick={() => {
                setName('');
              }}
              size="1x"
              color="white"
            />
          </div>
        </div>

        <div className="edit-info-title ">이메일</div>
        <div className="edit-info-email">duifusi@gmail.com</div>
        <div className="edit-info-marketing">마케팅 수신 동의</div>
        <div className="edit-info-notice ">이메일 수신에 동의하시면 Rebon 주식회사가 제공하는 서비스의 업데이트 및 이벤트 소식을 받을 수 있습니다.</div>

        <div class="select">
          <input type="radio" id="select" name="marcketing" />
          <label for="select">
            <FontAwesomeIcon icon={faCheck} />
          </label>
          동의 합니다.
          <span className="empty"></span>
          <input type="radio" id="select2" name="marcketing" />
          <label for="select2">
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
