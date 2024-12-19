[ December 5th, 2024 ]

_ t1 t2 _ _ p1 p2 _ _ _ _ n1 n2 _ _ loop d1 d2 d3 d4 _ _ _ 1
>>>>>->->>>>>>>>>>>>>>>>>+<<<<<<<

Read numbers until new line or EOF
,>+<[>-]>[-<++++++++++>>]<<----------[       Read next number
  [-->++++++[-<------>]]                     Convert digit to number
  ,>+<[>-]>[-<++++++++++>>]<<----------[     Read digits until new line or EOF
    [-->++++++[-<------>]]                   Convert digit to number
    ,>+<[>-]>[-<++++++++++>>]<<----------    Read next character
  ]

  Shift right if only 3 digits
  >>>>[<<<<<[->+<]<[->+<]<[->+<]>>>>>>>>]<<<<<<<<<

  Convert from base10 to base256
  <<<-<->>>>                        Initialize counter
  +[>>>>>                           Decrement base10 number until zero
    +<[>-]>[-<                      ___0
      +<[>-]>[-<                    __0_
        +<[>-]>[-<                  _0__
          +<[>-]>[-<                0___
            <-                      Exit loop if zero
          >++++++++++>>]<<-         Regroup
        >++++++++++>>]<<-           Regroup
      >++++++++++>>]<<-             Regroup
    >++++++++++>>]<<-               Regroup
    <<<<<<+<+[>-]>[-<<+>>>]>        Increment base256 counter
  ]
  >[-]>[-]>[-]>[-]<<<<<<<<<<<<<<    Clear base10 digits

  Compare with previous number
  [->>>>+<<<<]>[->+<]>>>>>[-<+<<<<<+>>>>>>]>[-<<<<+<<+>>>>>>]    Copy and rearrange for comparison
  +<<<[->>>>+[-]-<<<[->>>+<<]>[<]<<]>[>>>+<<<[-]]>>->            Compare most significant byte
  >+<[>-]>[-<<<<
    +<<<[->>>>+[-]-<<<[->>>+<<]>[<]<<]>[>>>+<<<[-]]>>->          Compare least significant byte if necessary
    +[->>+<<]>>->>                                               Move result to previous comparison
  ]<<
  -<+>[<-]<[-<<<<<<<<<+<+[>-]>[-<<+>>>]>>>>>>>]>>++[-]<<         Increment counter if increasing
  <<<<[-]>[-]>>>>>>>>                                            Clear second comparison values

  Read next character
  ,>+<[>-]>[-<++++++++++>>]<<----------
]

Convert from base256 to base10
<<<<<<<<-----------<----------<[-]----------<[-]----------<<<<<    Initialize counter
+[>>>                   Decrement base256 number until zero
  +<[>-]>[-<            _0
    +<[>-]>[-<          0_
      <-                Exit loop if zero
    >>>]<<-             Regroup
  >>>]<<-               Regroup
  >>>>>>>               Increment base10 counter
  +<+[>-]>[-<           ___0
    +<+[>-]>[-<         __0_
      +<+[>-]>[-<       _0__
        <+>             Increment thousands digit (output does not exceed four digits)
      ---------->>]<    Carry
    ---------->>]<      Carry
  ---------->>]<        Carry
  <<<<<<<<<
]

Print
>>>>>[++++++++++[<++++++++[->++++++<]>.[-]]>]++++++++++.
