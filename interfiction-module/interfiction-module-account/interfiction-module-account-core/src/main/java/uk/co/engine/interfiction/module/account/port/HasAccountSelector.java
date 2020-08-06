package uk.co.engine.interfiction.module.account.port;

import uk.co.engine.interfiction.module.account.domain.User;
import uk.co.engine.interfiction.shared.domain.EmailAddressVO;

import java.util.Optional;

public interface HasAccountSelector {

    Optional<User> findUsingId(EmailAddressVO userId);

}
