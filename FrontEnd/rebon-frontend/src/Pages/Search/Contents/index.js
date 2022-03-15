import styled from 'styled-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from '@fortawesome/free-solid-svg-icons';

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

        <Tags>
          <div>추천 태그</div>
          <Tag>영일대</Tag>
          <Tag>양덕</Tag>
          <Tag>구룡포</Tag>
          <Tag>칠포해수욕장</Tag>

          <Tag>영일대</Tag>
          <Tag>양덕</Tag>
          <Tag>구룡포</Tag>
          <Tag>칠포해수욕장</Tag>

          <Tag>구룡포</Tag>
          <Tag>칠포해수욕장</Tag>
        </Tags>
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

const Tags = styled.ul`
  font-size: 15px;
  padding: 0 5px;
  div {
    color: rgba(0, 0, 0, 0.3);
    grid-column: 1 / 5;
    margin-bottom: 10px;
  }
`;

const Tag = styled.li`
  width: fit-content;
  padding: 5px 7px;
  margin: 5px;
  float: left;
  border: 1px solid black;
  border-radius: 50px;
  font-weight: 500;
  &:hover {
    color: white;
    background-color: black;
  }
`;

export default Contents;
