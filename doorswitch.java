dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gameObj.getTurn().equals(constants.id))
                {
                    Toast.makeText(getApplicationContext(),"its your turn ",Toast.LENGTH_SHORT).show();
                    int rand = ((int)(Math.random()*100))%6 + 1;
                    int next = (Integer.valueOf(gameObj.getPlayers().get(Integer.valueOf(constants.id)).getCurPos()) + rand);
                    int nextPos = next % 16;
                    if(next>=16){
                        showBottomSheet(nextPos);
                    }

                    else if(isOwned(nextPos))
                    {
                        showOwnedDialogBox(String.valueOf(nextPos));
                    }
                    else
                    {
                        showNotOwnedDialogBox(String.valueOf(nextPos));
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Wait for your turn",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Toast.makeText(getApplicationContext(), "MY ID IS : "+constants.id, Toast.LENGTH_SHORT).show();
    }
