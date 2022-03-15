import styled from 'styled-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import Tags from '../Tags';
// 이 콘텐츠 이름은 다른 분들것과 따라 맞추자
function Contents() {
  return (
    <Wrapper>
      <ContentStyle>
        <Title>
          가고 싶은 지역을 입력해서 <br />
          <span>맛집, 숙소</span>를 찾아보세요!
        </Title>

        <InputBar>
          <Input />
          <FontAwesomeIcon icon={faSearch} className="search" />
        </InputBar>

        <Tags />
      </ContentStyle>
    </Wrapper>
  );
}

const Wrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 400px;
  height: 100vh;
  margin: 0 auto;
`;

const ContentStyle = styled.div`
  width: 22vw;
  display: grid;
  margin-top: -30px;
  grid-template-rows: repeat(3, 80px);
`;

const Title = styled.div`
  font-weight: 500;
  font-size: 22px;
  line-height: 1.2em;
  span {
    font-weight: bolder;
  }
`;

const InputBar = styled.div`
  margin-top: 10px;
  width: 95%;
  height: 35px;
  border-radius: 50px;
  border: 1px solid black;
`;

const Input = styled.input`
  padding-left: 10px;
  width: 90%;
  height: 35px;
  border-radius: 50px;
  background-color: transparent;
  border: none;
  &:focus {
    outline: none;
  }
`;

export default Contents;
