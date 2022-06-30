import React, { useState } from 'react';
import styled from 'styled-components';
import Modal, { ModalProvider, BaseModalBackground } from 'styled-react-modal';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';

import '../../../../styles/footprint-modal.css';

export default function FootprintModal() {
  const [isOpen, setIsOpen] = useState(false);
  const [opacity, setOpacity] = useState(0);
  const [modalState, setModalState] = useState({ list: 'block', delete: 'none', confirm: 'none' });

  function toggleModal(e) {
    setModalState({ list: 'block', delete: 'none', confirm: 'none' });
    setOpacity(0);
    setIsOpen(!isOpen);
  }

  function afterOpen() {
    setTimeout(() => {
      setOpacity(1);
    }, 100);
  }

  function beforeClose() {
    return new Promise((resolve) => {
      setOpacity(0);
      setTimeout(resolve, 300);
    });
  }

  const StyledModal = Modal.styled`
  width: 23rem;
  padding : 20px;
  border-radius:20px;
  background-color: white;
  opacity: ${(props) => props.opacity};
  transition : all 0.3s ease-in-out;
  `;

  const FadingBackground = styled(BaseModalBackground)`
    opacity: ${(props) => props.opacity};
    transition: all 0.3s ease-in-out;
  `;

  return (
    <div className="footprint-modal-wrapper">
      <ModalProvider backgroundComponent={FadingBackground}>
        <div className="footprint-modal" onClick={toggleModal}>
          리뷰삭제
        </div>

        <StyledModal
          isOpen={isOpen}
          afterOpen={afterOpen}
          beforeClose={beforeClose}
          onBackgroundClick={toggleModal}
          onEscapeKeydown={toggleModal}
          opacity={opacity}
          backgroundProps={{ opacity }}
        >
          <div style={{ display: modalState.list }}>
            <div className="footprint-modal-delete" onClick={toggleModal}>
              <button>
                <img src="../../../../image/edit.png" alt="edit-img" />
              </button>
              수정하기
            </div>

            <div
              className="footprint-modal-delete"
              onClick={() => {
                setModalState({ list: 'none', delete: 'block', confirm: 'none' });
              }}
            >
              <button>
                <img src="../../../../image/trash.png" alt="trash-img" />
              </button>
              삭제하기
            </div>

            <div className="footprint-modal-close" onClick={toggleModal}>
              <button>
                <FontAwesomeIcon icon={faXmark} />
              </button>
              닫기
            </div>
          </div>
          {/* 위가 visible하지 않은 상태, 삭제하기 클릭하면, 아래를 보여준다 */}

          <div className="footprint-delete-modal" style={{ display: modalState.delete }}>
            <button>
              <FontAwesomeIcon icon={faXmark} onClick={toggleModal} />
            </button>
            <div className="delete-message">리뷰를삭제하시겠습니까</div>
            <div
              className="delete-message-confirm"
              onClick={() => {
                setModalState({ list: 'none', delete: 'none', confirm: 'block' });
              }}
            >
              확인
            </div>
          </div>
          {/* 위가 visible하지 않은 상태, 삭제하기 클릭하면, 아래를 보여준다 */}

          <div className="footprint-confirm-modal" style={{ display: modalState.confirm }}>
            <button>
              <FontAwesomeIcon icon={faXmark} onClick={toggleModal} />
            </button>
            <div className="confirm-message">리뷰가 삭제되었습니다</div>
          </div>
        </StyledModal>
      </ModalProvider>
    </div>
  );
}
