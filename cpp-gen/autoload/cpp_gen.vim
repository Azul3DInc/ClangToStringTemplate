let s:pluginRoot = fnamemodify(expand('<sfile>:p:h'), ':h')
let s:cppgenPy = s:pluginRoot . "/cpp-gen.py"

" Autoload functions

function! cpp_gen#Generate(ast_template, output_source_path, output_header_path, stg_source_path, stg_source_instance_name, stg_header_path, stg_header_instance_name, output_json) abort

   let [l:source_file, l:header_file] = GetSourceHeaderFilePaths()

   let l:build_folder = GetBuildPath()

   let l:command = 'python3 ' . s:cppgenPy . ' ' . l:source_file . ' ' . l:header_file . ' ' . l:build_folder . "/compile_commands.json" . ' ' . a:stg_source_path . ' ' . a:stg_source_instance_name . ' ' . a:stg_header_path . ' ' . a:stg_header_instance_name
   let l:command = l:command . ' --ast-template ' . a:ast_template
   let l:command = l:command . ' --output-paths ' . a:output_source_path . ' ' . a:output_header_path 

   if a:output_json
      let l:command = l:command . " --output-json"
   endif

   let l:output_header_dir = fnamemodify(a:output_header_path, ':h')
   let l:output_source_dir = fnamemodify(a:output_source_path, ':h')

   if !isdirectory(l:output_header_dir) || !isdirectory(l:output_source_dir)
      let l:answer = input('Output directory paths do not exist. Would you like to create them? (y/n): ')
      echo "\n"
      if l:answer == 'y'
         call mkdir(l:output_header_dir, 'p')
         call mkdir(l:output_source_dir, 'p')
      else
         throw 'Invalid output directories ' . l:output_header_dir . ' ' . l:output_source_dir
      endif
   endif

   let l:output = system(l:command)

   if v:shell_error
      echohl ErrorMsg
      echo "Error executing cpp-gen.py"
   else
      echohl Normal
      echo "cpp-gen.py executed successfully"
   endif

   echo l:output
endfunction

function! cpp_gen#GenerateMock(output_json) abort
   let l:ast_template = "interface"

   try
      let [l:source_file, l:header_file] = GetSourceHeaderFilePaths()

      let l:output_source_path = fnamemodify(l:source_file, ':h')
      let l:output_source_filename = 'Mock' . fnamemodify(l:source_file, ':t')
      let l:output_source_path = l:output_source_path . '/Mocks/' . l:output_source_filename

      let l:output_header_path = fnamemodify(l:header_file, ':h')
      let l:output_header_filename = 'Mock' . fnamemodify(l:header_file, ':t')
      let l:output_header_path = l:output_header_path . '/Mocks/' . l:output_header_filename

      call cpp_gen#Generate(l:ast_template, l:output_source_path, l:output_header_path, "MockSource.stg", "MockImplementationFile", "MockHeader.stg", "MockSpecificationFile", a:output_json)
   catch
      echoerr "Exception: " . v:exception
   endtry
endfunction

" Helper Functions

function! GetBuildPath() abort
   let l:build_folder = expand('%:p:h')
   while !filereadable(l:build_folder . '/compile_commands.json') && !filereadable(l:build_folder . '/build' . '/compile_commands.json')
      let l:build_folder = fnamemodify(l:build_folder, ':h')
      if empty(l:build_folder)|| l:build_folder == '/'
       throw 'compile_commands.json not found!'
      endif
   endwhile
   
   let l:project_root = l:build_folder
   if filereadable(l:build_folder . '/build' . '/compile_commands.json')
      let l:build_folder = l:build_folder . '/build'
   endif

   return l:build_folder
endfunction

function! GetSourceHeaderFilePaths() abort
   let l:header_file = expand('%:p')
   let l:file_extension = expand('%:e')
   
   if l:file_extension !=# "h" || !filereadable(l:header_file) 
      throw "Invalid header file path: " . l:header_file
   endif

   let l:header_folder = fnamemodify(l:header_file, ':h')

   let l:source_filename = fnamemodify(l:header_file, ':t:r') . '.cpp'
   
   let l:source_folder = l:header_folder
   while !filereadable(l:source_folder . '/src/' . l:source_filename) && !filereadable(l:source_folder . '/' . l:source_filename)
      let l:source_folder = fnamemodify(l:source_folder, ':h')
      if empty(l:source_folder) || l:source_folder == '/'
         throw 'Cpp file not found!'
      endif
   endwhile

   if filereadable(l:source_folder . '/src/' . l:source_filename)
      let l:source_folder = l:source_folder . '/src'
   endif
   let l:source_file = l:source_folder . '/' . l:source_filename

   if !filereadable(l:source_file)
      throw "Invalid .cpp file path: " . l:source_file
    endif
   
    return [l:source_file, l:header_file]

 endfunction
