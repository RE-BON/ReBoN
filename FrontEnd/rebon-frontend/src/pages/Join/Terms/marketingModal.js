import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark, faCheck, faAngleLeft } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';
import '../../../styles/terms-modal.css';

export default function MarcketingModal() {
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
          <FontAwesomeIcon icon={faXmark} size="1x" />
        </div>
        <div className="terms-modal-contents">
          제 1 조 (목적)
          <br />
          <br />
          이 약관(이하 "약관"이라고 합니다)은 "RE-BON"(이하 "회사"라고 합니다)가 제공하는 검색광고 관련 제반 서비스의 이용과 관련하여 "광고주"와 "회원"과 "회사" 간에 필요한 사항을
          규정함을 목적으로 합니다.
          <br />
          <br />
          제 2 조 (정의)
          <br />
          <br />
          "약관"에서 사용하는 용어의 정의는 아래와 같습니다.
          <br />① "검색광고"라 함은 키워드와 연계하는 등 "회사"가 정한 방식에 따라 광고주가 신청한 광고를 광고매체 이용자에게 보여주는 방식의 인터넷 광고를 의미합니다.
          <br /> ② "검색광고 서비스"(이하 "서비스"라고 합니다)라 함은 "회사"가 제공하는 "검색광고" 및 "검색광고"에 부수되는 제반 서비스를 의미합니다.
          <br />③ "광고매체"라 함은 "회사"에 의해 "검색광고"가 게재되는 매체를 의미합니다.
          <br /> ④ "광고주"라 함은 "서비스"를 이용하기 위해 "약관"에 따라 "회사"와 이용계약을 체결하고 "회사"가 제공하는 "서비스"를 이용하는 이용고객을 의미합니다.
          <br /> ⑥ "광고주계정"이라 함은 "광고주"가 "검색광고"의 신청, 게재, 관리, 취소 등을 하기 위해 "검색광고센터"를 이용할 수 있도록, "광고주"가 등록을 신청하면 "회사"가
          "광고주" 에게 부여하는 식별정보를 의미합니다.
          <br />⑦ "광고소재"라 함은 "광고주"가 게재 신청하여 "광고매체"에 게재되는 키워드, 제목, 설명문구, 부가정보, 이미지 등 광고 내용을 의미합니다. ⑧ "비즈머니"라 함은
          "검색광고"를 이용하기 위해 사용할 수 있는 충전금으로 "검색광고"의 이용대금은 "비즈머니"를 통해서만 지불 가능합니다. "비즈머니"는 환불이 가능한 유상비즈머니와 환불이
          불가능한 무상비즈머니로 구분됩니다.
        </div>

        <div className="terms-select">
          <input type="checkbox" id="terms-select" name="marcketing" />
          <label for="terms-select">
            <FontAwesomeIcon icon={faCheck} />
          </label>
          위 사항을 모두 확인했고, 서비스 이용약관에 동의합니다
        </div>
        <div className="terms-modal-btn">확인</div>
      </div>
    </div>
  );
}
