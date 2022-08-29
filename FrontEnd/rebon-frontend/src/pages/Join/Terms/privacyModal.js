import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark, faCheck, faAngleLeft } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';
import '../../../styles/terms-modal.css';

export default function PrivacyModal() {
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
          Eleven(1절)
          <br />
          <br />
          따분한 나의 눈빛이 무표정했던 얼굴이 널 보며 빛나고 있어 널 담은 눈동자는 odd 내 안에 빼곡하게 피어나는 blue 내가 지금 느끼는 이 감정들은 true 내 입술을 간지럽힌 낯선 그
          이름 난 몰랐어 내 맘이 이리 다채로운지 긴 꿈을 꾸게 해 이 방은 작은 heaven 춤을 추곤 해 실컷 어지러울 만큼 Oh my, oh my God 한 칸 더 채우고 있어 잘 봐 one, two, three,
          four, five, six, seven You make me feel like eleven
          <br />
          <br />
          Eleven(2절) <br />
          투명한 너와 나의 사이 가만히 들여다보다 일렁인 물결 속으로 더 빠져드는 걸 그날 향기로운 보랏빛의 mood 셀 수 없이 반복해도 기분 좋은 꿈 감히 누가 이렇게 날 설레게 할 줄 난
          몰랐어 내 맘이 이리 다채로운지 긴 꿈을 꾸게 해 이 방은 작은 heaven 춤을 추곤 해 실컷 어지러울 만큼 Oh my, oh my God 한 칸 더 채우고 있어 잘 봐 one, two, three, four,
          five, six, seven You make me feel like eleven (hey) 내 앞에 있는 너를 (hey) 그 눈에 비친 나를 (그 눈에 비친 나를) Aya, aya, aya (가만히 바라봐) 내 앞에 있는 너를 그 눈에
          비친 나를 가만히 바라봐
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
