public class HelloSceneformActivity extends AppCompatActivity {
   private static final String TAG = HelloSceneformActivity.class.getSimpleName();
   private static final double MIN_OPENGL_VERSION = 3.0;

   private ArFragment arFragment;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

       if (!checkIsSupportedDeviceOrFinish(this)) {
           return;
       }

       setContentView(R.layout.activity_ux);
       arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);

       arFragment.setOnTapArPlaneListener(
               (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
                   Anchor anchor = hitResult.createAnchor();
                   placeObject(arFragment, anchor, Uri.parse("model.sfb"));

               });
   }

   private void placeObject(ArFragment arFragment, Anchor anchor, Uri uri) {
       ModelRenderable.builder()
               .setSource(arFragment.getContext(), uri)
               .build()
               .thenAccept(modelRenderable -> addNodeToScene(arFragment, anchor, modelRenderable))
               .exceptionally(throwable -> {
                           Toast.makeText(arFragment.getContext(), "Error:" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                           return null;
                       }

               );

   }

   private void addNodeToScene(ArFragment arFragment, Anchor anchor, Renderable renderable) {
       AnchorNode anchorNode = new AnchorNode(anchor);
       TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
       node.setRenderable(renderable);
       node.setParent(anchorNode);
       arFragment.getArSceneView().getScene().addChild(anchorNode);
       node.select();
   }


   public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
       if (Build.VERSION.SDK_INT < VERSION_CODES.N) {
           Log.e(TAG, "Sceneform requires Android N or later");
           Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
           activity.finish();
           return false;
       }
       String openGlVersionString =
               ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                       .getDeviceConfigurationInfo()
                       .getGlEsVersion();
       if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
           Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
           Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                   .show();
           activity.finish();
           return false;
       }
       return true;
   }


}
