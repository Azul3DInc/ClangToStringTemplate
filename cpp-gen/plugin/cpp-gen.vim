" Prevent duplicate loading:

if exists("g:CPP_GEN_Version") || &cp
   finish
endif

let g:CPP_GEN_Version="0.0.1"

if !has('python3')
   echoerr "Python3 is required for code generation" 
   finish
endif

command! GenerateMock call cpp_gen#GenerateMock(0)
command! GenerateMockWithJson call cpp_gen#GenerateMock(1)
