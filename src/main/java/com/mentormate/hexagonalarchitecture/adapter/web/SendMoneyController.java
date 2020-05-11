package com.mentormate.hexagonalarchitecture.adapter.web;


import com.mentormate.hexagonalarchitecture.application.port.in.SendMoneyUseCase;
import com.mentormate.hexagonalarchitecture.application.port.in.SendMoneyUseCase.SendMoneyCommand;
import com.mentormate.hexagonalarchitecture.common.WebAdapter;
import com.mentormate.hexagonalarchitecture.domain.Account.AccountId;
import com.mentormate.hexagonalarchitecture.domain.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
class SendMoneyController {

	private final SendMoneyUseCase sendMoneyUseCase;

	@PostMapping(path = "/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
	void sendMoney(
			@PathVariable("sourceAccountId") Long sourceAccountId,
			@PathVariable("targetAccountId") Long targetAccountId,
			@PathVariable("amount") Long amount) {

		SendMoneyCommand command = new SendMoneyCommand(
				new AccountId(sourceAccountId),
				new AccountId(targetAccountId),
				Money.of(amount));

		sendMoneyUseCase.sendMoney(command);
	}

}
