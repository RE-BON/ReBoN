import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark, faCheck, faAngleLeft } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';
import '../../styles/terms-modal.css';

export default function TermsModal() {
  const navigate = useNavigate();
  const goBack = () => {
    navigate(-1);
  };
  return (
    <div className="terms-modal-wrapper">
      <div className="terms-modal-box">
        <div className="terms-modal-title">
          <FontAwesomeIcon icon={faAngleLeft} onClick={goBack} size="1x" />
          서비스 이용약관
          <FontAwesomeIcon icon={faXmark} onClick={goBack} size="1x" />
        </div>
        <div class="terms-modal-contents">
          제1장 총칙 제1조(목적) <br /> <br /> ReBON 서비스를 이용해 주셔서 감사합니다. 지역 정보 웹앱 서비스를 제공하는 아래 약관을 읽어주시면 감사드리겠습니다.
          <br /> <br />
          제2조(정의)
          <br /> <br />이 약관에서 사용하는 용어의 정의는 다음과 같습니다. 1. “회사”라 함은 “콘텐츠” 산업과 관련된 경제활동을 영위하는 자로서 상품 및 용역을 판매하는 자를 말합니다.
          2. “이용자”라 함은 “회사”의 사이트에 접속하여 이 약관에 따라 “회사”가 제공하는 “콘텐츠” 및 제반서비스를 이용하는 회원 및 비회원을 말합니다. 3. “회원”이라 함은 “회사”와
          이용계약을 체결하고 “이용자” 아이디(ID)를 부여받은 “이용자”로서 “회사”의 정보를 지속적으로 제공받으며 “회사”가 제공하는 서비스를 지속적으로 이용할 수 있는 자를 말합니다
        </div>

        <div class="terms-select">
          <input type="checkbox" id="terms-select" name="marcketing" />
          <label for="terms-select">
            <FontAwesomeIcon icon={faCheck} />
          </label>
          위 사항을 모두 확인했고, 서비스 이용약관에 동의합니다
        </div>
        <Link to="/terms">
          <div className="terms-modal-btn">확인</div>
        </Link>
      </div>
    </div>
  );
}
