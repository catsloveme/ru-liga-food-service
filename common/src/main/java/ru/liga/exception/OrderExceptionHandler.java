package ru.liga.exception;



//@RestControllerAdvice
public class OrderExceptionHandler {//extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(OrderNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleNotFoundException(OrderNotFoundException exception) {
//        return ResponseEntity.badRequest()
//                .body(new ErrorResponse(
//                        "No such Order",
//                        String.valueOf(NOT_FOUND.value()),
//                        NOT_FOUND.name(),
//                        NOT_FOUND.getReasonPhrase()
//                ));
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> exception(Exception exception) {
//        return ResponseEntity.badRequest()
//                .body(new ErrorResponse(
//                        "Server error",
//                        String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
//                        INTERNAL_SERVER_ERROR.name(),
//                        INTERNAL_SERVER_ERROR.getReasonPhrase()
//                ));
//    }
}
