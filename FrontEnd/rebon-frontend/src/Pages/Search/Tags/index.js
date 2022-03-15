import styled from 'styled-components';

function Tags() {
  return (
    <Wrapper>
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
    </Wrapper>
  );
}
const Wrapper = styled.ul`
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

export default Tags;
