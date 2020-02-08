package tk.laurenfrost.mapservice.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "You have no authority here!")
public class UserHaveNoPermissionToModifyFile extends RuntimeException {}
